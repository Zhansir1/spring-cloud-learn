package com.zhansir.email;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;

public class Sender {
    public static void main(String[] args) {
        // SMTP 服务器地址和端口
        String host = "smtp.qq.com"; // Gmail SMTP 服务器
        String port = "587";  // Gmail 使用 587 端口（STARTTLS）

        // 发件人和收件人
        String from = "642101715@qq.com";  // 发送方邮箱
        String to = "zhanjiangcheng@xiaomi.com";  // 接收方邮箱

        // 用户认证信息
        final String username = "642101715@qq.com"; // 发送方邮箱的用户名
        final String password = "zhfahcwltblqbfbd"; // 发送方邮箱的密码

        // 设置 JavaMail 的属性
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");  // 启用 STARTTLS
        properties.put("mail.smtp.host", host);  // 设置 SMTP 服务器
        properties.put("mail.smtp.port", port);  // 设置 SMTP 端口

        // 获取会话并设置认证信息
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));  // 设置发件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));  // 设置收件人
            message.setSubject("HTML Email with CID Image");  // 设置邮件主题

            // 创建多部分消息对象
            MimeMultipart multipart = new MimeMultipart("related");

            // 创建正文部分，HTML 内容中包含一个图片标签，引用了图片的 CID
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<h1>This is an HTML email</h1>" +
                    "<p>This is a <b>bold</b> paragraph.</p>" +
                    "<p>Here is an <a href='https://www.baidu.com'>example link</a>.</p>" +
                    "<p>Here is an inline image:</p>" +
                    "<img src='cid:image001'>";  // 这里的 'cid:image001' 将与下面的图片部分关联
            htmlPart.setContent(htmlContent, "text/html");

            // 创建附件部分，包含图片，使用 CID 作为唯一标识符
            MimeBodyPart imagePart = new MimeBodyPart();
            String imagePath = "C:\\Users\\MI\\Downloads\\【哲风壁纸】街景-风景.png"; // 图片路径

            // 将图片文件转换为字节流
            File imageFile = new File(imagePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (InputStream imageStream = new FileInputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = imageStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            byte[] array = byteArrayOutputStream.toByteArray();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(array, "image/png"); // 图片路径
            imagePart.setDataHandler(new DataHandler(dataSource));
            imagePart.setHeader("Content-ID", "<image001>");  // 设置 Content-ID

            // 将正文和图片部分添加到多部分消息中
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(imagePart);

            // 设置邮件内容为多部分消息
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);

            System.out.println("HTML Email with CID image sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
