```plantuml
hide empty member
interface FieldId {
    String code()
}
interface TagId {

}
interface FieldInfo {
    Class<?> type()
    Class<?>[] genericTypes()
    boolean isCodeLookup()
    boolean isCodeValuable()
    boolean isTransient()
}
interface PathConstraint {

}
interface FieldModel {
    <T> T get(FieldId fieldId)
    <T> void set(FieldId fieldId, T value)
    default <T> T get(DslField<T> field)
    default <T> void set(DslField<T> field, T value)
}
abstract class AbstractWrapper<M> {
    M getModel()
    List<FieldInfo> getFieldInfos()
}
class BaseFieldModel {
    TypeAdapterRegistry TYPE_ADAPTER_REGISTRY
    List<FieldInfo> getFieldInfos()
}
FieldModel <|.. AbstractWrapper
FieldModel <|.. BaseFieldModel

FieldId *- TagId
FieldInfo *--"1" FieldId
FieldInfo *---"n" FieldId : siblings

AbstractWrapper *-- FieldInfo
BaseFieldModel *-- FieldInfo
```