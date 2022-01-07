package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class OrderAnswerRequest extends Request<OrderAnswerRequest> {


    Long orderId;

    String answer;

    public OrderAnswerRequest(Long orderId, String answer) {
        this.orderId = orderId;
        this.answer = answer;
    }


    public OrderAnswerRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public OrderAnswerRequest encrypt(PublicKey key) {
        return null;
    }

    @Override
    public OrderAnswerRequest decrypt(PrivateKey key) {
        OrderAnswerRequest result = new OrderAnswerRequest();

        result.setUserId(this.userId);
        result.setOrderId(this.orderId);
        result.setAnswer(RSA.decrypt(this.answer, key));
        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        try {

            Signature theirSig = Signature.getInstance("SHA1withRSA");
            theirSig.initVerify(publicKey);
            theirSig.update(this.answer.getBytes());

            if (theirSig.verify(this.userSign.getBytes())) {
                System.out.println("Signature checks out.");
            } else {
                System.out.println("Signature failed. Possible imposter found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    public String toString() {
        return "OrderAnswerRequest{" +
                "orderId=" + orderId +
                ", answer='" + answer + '\'' +
                ", userId=" + userId +
                '}';
    }
}
