//package pro.dengyi.myhome.myhomeutil;
//
//import org.apache.commons.codec.binary.Base64;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//
///**
// * @author BLab
// */
//public class AliIotUtil {
//
//    /**
//     * 计算签名，password组装方法，请参见AMQP客户端接入说明文档。
//     */
//    public static String doSign(String toSignString, String secret, String signMethod) throws Exception {
//        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), signMethod);
//        Mac mac = Mac.getInstance(signMethod);
//        mac.init(signingKey);
//        byte[] rawHmac = mac.doFinal(toSignString.getBytes());
//        return Base64.encodeBase64String(rawHmac);
//    }
//}
