package com.gcp.finance.ucd.controller.convert;

<#assign voCls = "${table.entityName?replace('DO','VO')}">
<#assign comIns = "${table.entityPath?replace('DO','')}">
<#assign getprefix="get"/>
<#assign voConvertCls = "${table.entityName?replace('DO','Convert')}">
import com.sys.web.controller.vo.${voCls};
import com.sys.entity.${entity};

import java.util.ArrayList;
import java.util.List;

public class ${voConvertCls} {

    public static ${entity} to${entity}(${voCls} obj){
        if(obj == null){
            return null;
        }

        ${entity} ret = new ${entity}();

<#list table.fields as field>
    <#if fs?seq_contains(field.propertyName) = false>
        ret.set${field.capitalName}(obj.${getprefix}${field.capitalName}());
    </#if>
</#list>

        return ret;
    }

    public static ${voCls} from(${entity} obj){
        if(obj == null){
            return null;
        }

        ${voCls} ret = new ${voCls}();
<#list table.fields as field>
    <#if fs?seq_contains(field.propertyName) = false>
        ret.set${field.capitalName}(obj.${getprefix}${field.capitalName}());
    </#if>
</#list>

        return ret;
    }

    public static List<${voCls}> from(List<${entity}> list){
        if(list == null){
            return null;
        }

        List<${voCls}> retList = new ArrayList<>();
        for (${entity} item : list) {
            retList.add(from(item));
        }

        return retList;
    }

}