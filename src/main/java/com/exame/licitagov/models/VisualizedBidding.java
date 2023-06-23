package com.exame.licitagov.models;


import jakarta.persistence.*;

@Entity
public class VisualizedBidding {

    public VisualizedBidding(Long userId, Long biddingId) {
        this.userId = userId;
        this.biddingId = biddingId;
    }

    public VisualizedBidding() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;

    private Long biddingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public Long getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(Long biddingId) {
        this.biddingId = biddingId;
    }

    public static class Builder {

        private Long userId;

        private Long biddingId;

        public VisualizedBidding.Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public VisualizedBidding.Builder withBidding(Long biddingId){
            this.biddingId = biddingId;
            return this;
        }


        public VisualizedBidding build(){
            return new VisualizedBidding(userId, biddingId);
        }
    }
}
