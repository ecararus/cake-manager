package com.waracle.cakemgr.formater;

import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.vo.CakeVO;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

public final class CakeTransformer {

    private CakeTransformer() {
        throw new UnsupportedOperationException();
    }

    public static Cake toDomain(CakeVO cakeVO) {
        return new Cake(cakeVO.getTitle(), cakeVO.getDesc(), cakeVO.getImage());
    }

    public static CakeVO toVo(Cake cake) {
        return CakeVO.builder()
                .setTitle(cake.getTitle())
                .setDesc(cake.getDesc())
                .setImage(cake.getImage())
                .build();
    }

    public static Iterable<CakeVO> toVos(Iterable<Cake> cakes) {
        return stream(cakes.spliterator(), false)
                .map(CakeTransformer::toVo)
                .collect(toList());
    }


    public static String toHumanReadable(CakeVO cakeVO) {
        return "title='" + cakeVO.getTitle() + '\'' +
                ", desc='" + cakeVO.getDesc() + '\'' +
                ", image='" + cakeVO.getImage() + '\'' +
                "\r\n";
    }

}
