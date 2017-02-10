package com.eca.cakemgr.test.data;

import com.eca.cakemgr.formater.CakeTransformer;
import com.eca.cakemgr.domain.Cake;
import com.eca.cakemgr.vo.CakeVO;

public final class CakeMother {

    private CakeMother() {
        throw new UnsupportedOperationException();
    }

    public static Cake produceCake() {
        return CakeTransformer.toDomain(produceCakeVo("CakeTitle", "CakeDescription", "ImageLocation"));
    }

    public static CakeVO produceCakeVO() {
        return produceCakeVo("CakeTitle", "CakeDescription", "ImageLocation");
    }

    public static CakeVO produceCakeVo(String title, String desc, String image) {
        return CakeVO.builder().setDesc(desc).setTitle(title).setImage(image).build();
    }
}
