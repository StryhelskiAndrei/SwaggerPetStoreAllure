package com.itspartner.petstore;

public class Order extends PetStore {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public static class Builder {
        private Order mOrder = new Order();

        public Builder setId(int id) {
            this.mOrder.id = id;
            return this;
        }

        public Builder setPetId(int petId) {
            this.mOrder.petId = petId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.mOrder.quantity = quantity;
            return this;
        }

        public Builder setShipDate(String shipDate) {
            this.mOrder.shipDate = shipDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.mOrder.status = status;
            return this;
        }

        public Builder setComplete(boolean complete) {
            this.mOrder.complete = complete;
            return this;
        }

        public Order build() {
            return mOrder;
        }
    }

    public static Order createPositiveOrder() {
        Order order = new Order.Builder()
                .setId(getRandomId())
                .setPetId(getRandomId())
                .setQuantity(3)
                .setComplete(true)
                .setStatus("available")
                .setShipDate("2021-06-14T07:46:14.469Z")
                .build();

        return order;
    }

    public static Order createPositiveOrder(int id) {
        Order order = new Order.Builder()
                .setId(id)
                .setPetId(getRandomId())
                .setQuantity(3)
                .setComplete(true)
                .setStatus("available")
                .setShipDate("2021-06-14T07:46:14.469Z")
                .build();

        return order;
    }
}
