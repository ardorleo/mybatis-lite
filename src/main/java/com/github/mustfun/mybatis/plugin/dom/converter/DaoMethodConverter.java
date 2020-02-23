package com.github.mustfun.mybatis.plugin.dom.converter;

import com.github.mustfun.mybatis.plugin.dom.model.Mapper;
import com.github.mustfun.mybatis.plugin.util.JavaUtils;
import com.github.mustfun.mybatis.plugin.util.MapperUtils;
import com.intellij.psi.PsiMethod;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.DomElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * @author yanglin
 * @update itar dao层method 转变对象
 */
public class DaoMethodConverter extends AbstractConverterAdaptor<PsiMethod> {

    @Nullable
    @Override
    public PsiMethod fromString(@Nullable @NonNls String id, ConvertContext context) {
        //获取唤醒的当前元素的mapper对象
        Mapper mapper = MapperUtils.getMapper(context.getInvocationElement());
        //找到当前mapper的方法
        return JavaUtils.findMethod(context.getProject(), MapperUtils.getNamespace(mapper), id).orElse(null);
    }

    @NotNull
    @Override
    public Collection<? extends PsiMethod> getVariants(ConvertContext context) {
        Mapper mapper = MapperUtils.getMapper(context.getInvocationElement());
        return JavaUtils.findMethods(context.getProject(), MapperUtils.getNamespace(mapper));
    }
}