```plantuml
hide empty member
interface FieldPath {
    Class<?> getBaseClass()
    List<ReadMethodRef<?, ?>> getPath()
    FieldId getFieldId()
    PathConstraint getConstraint()
    String getReadable()
    ReadMethodRef getReadMethod()
    WriteMethodRef getWriteMethod()
    boolean isTransient()
}
class DefaultFieldPath {
    Class<?> baseClass
    List<ReadMethodRef<?, ?>> path
    FieldId fieldId
    PathConstraint constraint
    String readable
    boolean _transient
    ReadMethodRef readMethodReference
    WriteMethodRef writeMethodReference
}
interface FieldPathProvider {
    List<FieldPath> values()
}
class PathBuilder<B, C, T> {
    Class<B> baseClass
    List<ReadMethodRef<?, ?>> pathList
    FieldId fieldId
    PathConstraint constraint
    boolean _transient = false
    ReadMethodRef<C, ?> readMethodRef
    WriteMethodRef<C, T> writeMethodRef
}
interface ReadMethodRef<T, R> {
    R call(T t);
}
interface WriteMethodRef<T, R> {
    void call(T t, R a1);
}
FieldPath <|.. DefaultFieldPath
ReadMethodRef --* FieldPath
FieldPath *-- WriteMethodRef
FieldPathProvider *- FieldPath
PathBuilder --> FieldPath
```