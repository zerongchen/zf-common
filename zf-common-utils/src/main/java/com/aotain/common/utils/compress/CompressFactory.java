package com.aotain.common.utils.compress;


import com.aotain.common.utils.compress.gzip.GzipCompress;
import com.aotain.common.utils.compress.gzip.GzipDecompress;
import com.aotain.common.utils.compress.lzo.LzoCompress;
import com.aotain.common.utils.compress.lzo.LzoDecompress;
import com.aotain.common.utils.compress.tar.TarCompress;
import com.aotain.common.utils.compress.tar.TarDecompress;
import com.aotain.common.utils.compress.zip.ZipCompress;
import com.aotain.common.utils.compress.zip.ZipDecompress;


/**
 * 压缩/解压工厂类
 * @author Administrator
 *
 */
public class CompressFactory {

    public static ICompressDecompress createCompressDecompress(CompressType type,
                                                               CompressFormat format) {

        ICompressDecompress process = null;

        if (type == CompressType.COMPRESS) {
            switch (format) {
                case ZIP:
                    process = new ZipCompress();
                    break;

                case GZIP:
                    process = new GzipCompress();
                    break;

                case LZO:
                    process = new LzoCompress();
                    break;

                case TAR:
                    process = new TarCompress();
                    break;
            }
        }
        else if (type == CompressType.DECOMPRESS) {
            switch (format) {
                case ZIP:
                    process = new ZipDecompress();
                    break;

                case GZIP:
                    process = new GzipDecompress();
                    break;

                case LZO:
                    process = new LzoDecompress();
                    break;

                case TAR:
                    process = new TarDecompress();
                    break;
            }
        }

        return process;
    }
}
