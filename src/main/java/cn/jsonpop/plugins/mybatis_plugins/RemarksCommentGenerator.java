package cn.jsonpop.plugins.mybatis_plugins;

import org.mybatis.generator.internal.*;
import org.mybatis.generator.internal.util.*;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import java.text.*;
import java.util.*;

/**
 * description 生成实体类注释
 * 创建时间 2018/10/19
 *
 * @author 仇兴洲
 */
public class RemarksCommentGenerator extends DefaultCommentGenerator {
    public void addModelClassComment(final TopLevelClass topLevelClass, final IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("/**");
        final String remarks = introspectedTable.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            final String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            String[] array;
            for (int length = (array = remarkLines).length, i = 0; i < length; ++i) {
                final String remarkLine = array[i];
                topLevelClass.addJavaDocLine(" * " + remarkLine);
            }
        }
        topLevelClass.addJavaDocLine(" * ");
        final StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        topLevelClass.addJavaDocLine(sb.toString());
        topLevelClass.addJavaDocLine(" *");
        this.addJavadocTag((JavaElement)topLevelClass, false);
        topLevelClass.addJavaDocLine(" */");
    }

    public void addFieldComment(final Field field, final IntrospectedTable introspectedTable, final IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        final String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            final String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            String[] array;
            for (int length = (array = remarkLines).length, i = 0; i < length; ++i) {
                final String remarkLine = array[i];
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        this.addJavadocTag((JavaElement)field, false);
        field.addJavaDocLine(" */");
        /*if (field.getType().toString().contains("java.util.Date")) {
            field.setType(FullyQualifiedJavaType.getStringInstance());
        }*/
    }

    public void addFieldComment(final Field field, final IntrospectedTable introspectedTable) {
        field.addJavaDocLine("/**");
        this.addJavadocTag((JavaElement)field, false);
        field.addJavaDocLine(" */");
    }

    public void addGetterComment(final Method method, final IntrospectedTable introspectedTable, final IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        this.addJavadocTag((JavaElement)method, false);
        method.addJavaDocLine(" */");
    }

    public void addSetterComment(final Method method, final IntrospectedTable introspectedTable, final IntrospectedColumn introspectedColumn) {
        method.addJavaDocLine("/**");
        this.addJavadocTag((JavaElement)method, false);
        method.addJavaDocLine(" */");
    }

    protected void addJavadocTag(final JavaElement javaElement, final boolean markAsDoNotDelete) {
        final StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@generation date");
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        final String s = this.getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    protected String getDateString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
