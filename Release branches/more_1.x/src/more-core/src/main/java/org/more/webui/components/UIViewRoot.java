/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.more.webui.components;
import java.util.HashMap;
import java.util.Map;
import org.more.core.global.Global;
import org.more.webui.ViewContext;
import org.more.webui._.Register;
import org.more.webui.freemarker.parser.ElementHook;
import org.more.webui.freemarker.parser.Hook_UserTag;
import org.more.webui.freemarker.parser.TemplateScanner;
/**
 * ��������ĸ���ͬʱҲ���𱣴�������ͼ������
 * @version : 2012-3-29
 * @author ������ (zyc@byshell.org)
 */
public class UIViewRoot extends UIComponent {
    private Map<String, UIParamter> params = new HashMap<String, UIParamter>();
    private UIViewRoot() {};
    @Override
    public String getComponentType() {
        return "WebUI.ViewRoot";
    }
    /**��ȡһ���������*/
    public UIParamter getParamter(String name) {
        return this.params.get(name);
    }
    /**��ȡ����Ĳ���Map*/
    public Map<String, UIParamter> getParamters() {
        return this.params;
    }
    /**����һ��������������������ظ��µĻ��滻�ɵġ�*/
    public void addParamter(UIParamter uip) {
        if (uip != null)
            this.params.put(uip.getName(), uip);
    }
    /**����һ��������������������ظ��µĻ��滻�ɵġ�*/
    public void addParamter(String key, Object value) {
        UIParamter uip = new UIParamter();
        uip.setName(key);
        uip.setValue(value);
        this.addParamter(uip);
    }
    /*----------------------------------------------------------------------------------*/
    /**���ڴ���һ��{@link UIViewRoot}���� */
    public static UIViewRoot createViewRoot(ViewContext viewContext) throws Throwable {
        Global uiConfig = viewContext.getUIContext().getGlobal();
        Register comRegister = new Register(uiConfig);
        //B.����ɨ����
        ElementHook hook = new Hook_UserTag(comRegister);/*UnifiedCall��@add*/
        TemplateScanner scanner = new TemplateScanner();
        scanner.addElementHook("UnifiedCall", hook);
        //C.����ģ���ȡUIViewRoot
        return scanner.parser(viewContext.getTemplate(), new UIViewRoot());
    }
}