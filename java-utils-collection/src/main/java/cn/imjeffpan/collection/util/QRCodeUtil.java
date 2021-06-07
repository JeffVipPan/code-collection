package cn.imjeffpan.collection.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {
    /**
     * 生成二维码
     *
     * @param text   储存内容
     * @param width  宽度
     * @param height 高度
     * @return
     */
    public static String getQRCodeBase64(String text, int width, int height) {
        QrConfig config = new QrConfig(width, height);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(1);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.WHITE.getRGB());
        // 生成二维码到文件，也可以到流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate(text, config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] pngData = outputStream.toByteArray();
        // 这个前缀，可前端加，可后端加，不加的话，不能识别为图片
        return "data:image/png;base64," + Base64.encode(pngData);
    }

    /**
     * 生成二维码
     *
     * @param content 二维码的内容
     * @return BitMatrix对象
     */
    public static BitMatrix createCode(String content) throws IOException {
        // 二维码的宽高
        int width = 1000;
        int height = 1000;

        // 其他参数，如字符集编码
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 容错级别为H
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 白边的宽度，可取0~4
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = null;
        try {
            // 生成矩阵，因为我的业务场景传来的是编码之后的URL，所以先解码
            bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);

            // bitMatrix = deleteWhite(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitMatrix;
    }

    /**
     * 删除生成的二维码周围的白边，根据审美决定是否删除
     *
     * @param matrix BitMatrix对象
     * @return BitMatrix对象
     */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }


    /**
     * 测试类
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com";
        BitMatrix bitMatrix = QRCodeUtil.createCode(url);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", outputStream);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        System.out.println(byteArrayInputStream);

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        String filePath = "/Users/imjeffpan/Desktop";
        String fileName = "1.jpg";
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file = new File(filePath + "//" + fileName);
        fos = new FileOutputStream(file);
        bos = new BufferedOutputStream(fos);
        bos.write(outputStream.toByteArray());
        bos.close();
        fos.close();

    }

}