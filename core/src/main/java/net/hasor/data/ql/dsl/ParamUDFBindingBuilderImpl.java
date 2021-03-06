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
package net.hasor.data.ql.dsl;
import net.hasor.data.ql.dsl.domain.EqType;
/**
 * {@link UDFBindingBuilder} 接口实现类，用于协助构造 DataQL 查询模型。
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-03-23
 */
class ParamUDFBindingBuilderImpl extends BindingBuilderWraper implements UDFBindingBuilder {
    private String            paramName;
    private UDFBindingBuilder udfBuilder;
    public ParamUDFBindingBuilderImpl(String paramName, UDFBindingBuilder udfBuilder) {
        super(udfBuilder);
        this.paramName = paramName;
        this.udfBuilder = udfBuilder;
    }
    //
    @Override
    public UDFBindingBuilder addParam(DataParam dataParam) {
        return this.addParam(dataParam, EqType.EQ);
    }
    @Override
    public UDFBindingBuilder addParam(DataParam dataParam, EqType eqType) {
        this.udfBuilder.addParam(dataParam, eqType);
        return this;
    }
    @Override
    public String getName() {
        return this.paramName;
    }
    //
    @Override
    public DataField asField() {
        return new QueryField(this.getName(), this.buildQuery().getDomain());
    }
    @Override
    public DataParam asParam() {
        return new QueryParam(this.getName(), this.buildQuery().getDomain());
    }
}