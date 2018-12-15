package cn.jsonpop.plugins.mybatis_plugins;

import java.util.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.*;

/**
 * description Lombok插件
 * 省去GETTER、SETTER方法
 * 创建时间 2018/10/19
 *
 * @author 仇兴洲
 */
public class LombokPlugin extends PluginAdapter {
    public boolean validate(final List<String> list) {
        return true;
    }

    public boolean modelBaseRecordClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.addLombokAnnotation(topLevelClass, introspectedTable);
        return true;
    }

    public boolean modelExampleClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.addLombokAnnotation(topLevelClass, introspectedTable);
        return true;
    }

    public boolean modelPrimaryKeyClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.addLombokAnnotation(topLevelClass, introspectedTable);
        return true;
    }

    public boolean modelRecordWithBLOBsClassGenerated(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        this.addLombokAnnotation(topLevelClass, introspectedTable);
        return true;
    }

    private void addLombokAnnotation(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.*");
        topLevelClass.addAnnotation("@Data");
    }

    public boolean modelGetterMethodGenerated(final Method method, final TopLevelClass topLevelClass, final IntrospectedColumn introspectedColumn, final IntrospectedTable introspectedTable, final Plugin.ModelClassType modelClassType) {
        return false;
    }

    public boolean modelSetterMethodGenerated(final Method method, final TopLevelClass topLevelClass, final IntrospectedColumn introspectedColumn, final IntrospectedTable introspectedTable, final Plugin.ModelClassType modelClassType) {
        return false;
    }
}
