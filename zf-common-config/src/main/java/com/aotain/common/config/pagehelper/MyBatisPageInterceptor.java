package com.aotain.common.config.pagehelper;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/02/28
 */
@Intercepts( {
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}) })
public class MyBatisPageInterceptor implements Interceptor {

    /** 数据库类型，不同的数据库有不同的分页方法 */
    private String databaseType = "mysql";

    /**
     * 拦截后要执行的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，
        //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，
        //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是
        //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个
        //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、
        //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。
        //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候
        //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        //通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql boundSql = delegate.getBoundSql();
        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
        Object obj = boundSql.getParameterObject();


        //这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。
        if (obj instanceof Page<?>) {
            Page<?> page = (Page<?>) obj;
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //拦截到的prepare方法参数是一个Connection对象
            Connection connection = (Connection) invocation.getArgs()[0];
            //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
            String sql = boundSql.getSql();

            //获取查询条件sql
            String searchSql = this.getSearchSql(page, sql);
            String notEqual = this.getNotEqualSql(page,searchSql);
            String timeScopeSql = this.getTimeScopeSql(page,notEqual);
            String fuzzySearchSql = getFuzzySearchSql(page,timeScopeSql);

            //给当前的page参数对象设置总记录数
            this.setTotalRecord(page,
                    fuzzySearchSql, connection);

            //获取排序sql
            String orderBySql = getOrderBySql(page,fuzzySearchSql);
            //获取分页Sql语句
            String pageSql = this.getPageSql(page, orderBySql);
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
            //*** zq :在框架取值的时候，会从两个地方取值。主要的是在 ParameterHandler 中，所以在这里。需要把参数给替换成map
            //org.apache.ibatis.reflection.MetaObject  类中 123 行 ， return objectWrapper.get(prop); 取值是取ParameterHandler中的。所以需要替换掉里面的参数
            ReflectUtil.setFieldValue(boundSql, "parameterObject", page.getParams());
            ParameterHandler parameterHandler = delegate.getParameterHandler();
            ReflectUtil.setFieldValue(parameterHandler, "parameterObject", page.getParams());

        }
        return invocation.proceed();
    }


    /**
     * 拦截器对应的封装原始对象的方法
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置注册拦截器时设定的属性
     */
    @Override
    public void setProperties(Properties properties) {
        this.databaseType = "mysql";
    }

    /**
     * 获取拼接上查询条件后的sql
     * @param page
     * @param sql
     * @return
     */
    private String getSearchSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if (page.getParams().size()!=0){
            for (Map.Entry<String,Object> entry:page.getParams().entrySet()){
                if (entry.getValue()==null||"".equals(entry.getValue())){
                    continue;
                }
                if (sqlBuffer.toString().contains("where") || sqlBuffer.toString().contains("WHERE")){
                    sqlBuffer.append(" AND  ").append( entry.getKey() ).append("=").append(entry.getValue());
                } else {
                    sqlBuffer.append(" WHERE 1 = 1 AND  ").append( entry.getKey() ).append("=").append("'").append(entry.getValue()).append("'");
                }
            }
        }
        return sqlBuffer.toString();
    }


    /**
     * 获取拼接上时间查询后的sql
     * @param page
     * @param sql
     * @return
     */
    private String getTimeScopeSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if (page.getTimeScopeParams().size()!=0){
            for (Map.Entry<String,TimeScope> entry:page.getTimeScopeParams().entrySet()){
                String startTime = entry.getValue().getStartTime();
                String endTime = entry.getValue().getEndTime();
                if ( StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime) ){
                    continue;
                }
                // 开始时间非空
                if ( !StringUtils.isEmpty(startTime) ) {
                    if (sqlBuffer.toString().contains("where") || sqlBuffer.toString().contains("WHERE")){
                        sqlBuffer.append(" AND  ").append( entry.getKey() ).append(" >= ").append("'").append(startTime).append("'");
                    } else {
                        sqlBuffer.append(" WHERE 1 = 1 AND  ").append( entry.getKey() ).append(" >= ").append("'").append(startTime).append("'");
                    }
                }
                // 开始时间非空
                if ( !StringUtils.isEmpty(endTime) ) {
                    if (sqlBuffer.toString().contains("where") || sqlBuffer.toString().contains("WHERE")){
                        sqlBuffer.append(" AND  ").append( entry.getKey() ).append(" <= ").append("'").append(endTime).append("'");
                    } else {
                        sqlBuffer.append(" WHERE 1 = 1 AND  ").append( entry.getKey() ).append(" <= ").append("'").append(endTime).append("'");
                    }
                }

            }
        }
        return sqlBuffer.toString();
    }

    /**
     * 获取拼接上查询条件后的sql
     * @param page
     * @param sql
     * @return
     */
    private String getFuzzySearchSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if (page.getFuzzyParams().size()!=0){
            for (Map.Entry<String,Object> entry:page.getFuzzyParams().entrySet()){
                if ( StringUtils.isEmpty(entry.getValue()) ){
                    continue;
                }
                if (sqlBuffer.toString().contains("where") || sqlBuffer.toString().contains("WHERE")){
                    sqlBuffer.append(" AND  ").append( entry.getKey() ).append(" LIKE").append(" '%").append(entry.getValue()).append("%' ");
                } else {
                    sqlBuffer.append(" WHERE 1 = 1 AND  ").append( entry.getKey() ).append(" LIKE").append(" '%").append(entry.getValue()).append("%' ");
                }
            }
        }
        return sqlBuffer.toString();
    }

    /**
     * 获取拼接上不等于查询条件后的sql
     * @param page
     * @param sql
     * @return
     */
    private String getNotEqualSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if (page.getNotEqualOperateParams().size()!=0){
            for (Map.Entry<String,Object> entry:page.getNotEqualOperateParams().entrySet()){
                if ( StringUtils.isEmpty(entry.getValue()) ){
                    continue;
                }
                if (sqlBuffer.toString().contains("where") || sqlBuffer.toString().contains("WHERE")){
                    sqlBuffer.append(" AND  ").append( entry.getKey() ).append(" != ").append(entry.getValue());
                } else {
                    sqlBuffer.append(" WHERE 1 = 1 AND  ").append( entry.getKey() ).append(" != ").append(entry.getValue());
                }
            }
        }
        return sqlBuffer.toString();
    }


    /**
     * 拼接排序字段
     * @param page
     * @param sql
     * @return
     */
    private String getOrderBySql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if (page.getOrderByParams().size()!=0){
            for (Map.Entry<String,Object> entry:page.getOrderByParams().entrySet()){
                if ( StringUtils.isEmpty(entry.getValue()) ){
                    continue;
                }
                sqlBuffer.append(" ORDER BY ").append( entry.getKey() ).append(" ").append( entry.getValue() );
            }
        }
        return sqlBuffer.toString();
    }


    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle
     * 其它的数据库都 没有进行分页
     *
     * @param page 分页对象
     * @param sql 原sql语句
     * @return
     */
    private String getPageSql(Page<?> page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMysqlPageSql(page, sqlBuffer);
        } else if ("oracle".equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(page, sqlBuffer);
        }
        return sqlBuffer.toString();
    }

    /**
     * 获取Mysql数据库的分页查询语句
     * @param page 分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        int offset = (page.getPage() - 1) * page.getPageSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
        return sqlBuffer.toString();
    }

    /**
     * 获取Oracle数据库的分页查询语句
     * @param page 分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Oracle数据库的分页查询语句
     */
    private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer) {
        //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
        int offset = (page.getPage() - 1) * page.getPageSize() + 1;
        sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
        //上面的Sql语句拼接之后大概是这个样子：
        //select * from (select u.*, rownum r from (select * from t_user) u where rownum < 31) where r >= 16
        return sqlBuffer.toString();
    }

    /**
     * 给当前的参数对象page设置总记录数
     *
     * @param page Mapper映射语句对应的参数对象
     * @param searchSql
     * @param connection 当前的数据库连接
     */
    private void setTotalRecord(Page<?> page,
                                String searchSql, Connection connection) {
        String countSql = this.getCountSql(searchSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            //通过parameterHandler给PreparedStatement对象设置参数
            //parameterHandler.setParameters(pstmt);
            //之后就是执行获取总记录数的Sql语句和获取结果了。
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                //给当前的参数page对象设置总记录数
                page.setTotalRecord(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        return "select count(*) from (" + sql + ") countSql";
//        int indexLo = sql.indexOf("from");
//        int indexUp = sql.indexOf("FROM");
//        int index = 0;
//        if(indexLo != -1){
//            index = indexLo;
//        }
//        if(indexUp != -1){
//            index = indexUp;
//        }
//        if (indexLo == -1 && indexUp == -1){
//            return "select count(*) from (" + sql.substring(index) + ") ban";
//        }else {
//            return "select count(*) " + sql.substring(index);
//        }
    }

}
