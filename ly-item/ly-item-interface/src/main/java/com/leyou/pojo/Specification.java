package com.leyou.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_specification")
public class Specification  implements Serializable {

    private static final long serialVersionUID = -3137434892299881898L;
    @Id
    @Column(name = "category_id")
    private long categoryId;//不要携程_id
    private String specifications;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Specification(long categoryId, String specifications) {
        this.categoryId = categoryId;
        this.specifications = specifications;
    }

    public Specification() {
    }
}
