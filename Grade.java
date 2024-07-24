package app.telecom.dto;

import java.math.BigDecimal;

public class Grade {
    private int id;
    private String name;
    private BigDecimal discountRate;

    public Grade(int id, String name, BigDecimal discountRate) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
    }

    // Getter와 Setter 메서드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
