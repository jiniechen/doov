```plantuml
hide empty member
class FieldChainBuilder<B, T, R> {

    private final Class<B> rootType
    private final List<PathMethod<Object, Object>> chain
    private final FieldId id
    private String readable
    private FieldId[] siblings
    private boolean isTransient
}
class GenericModel{
    List<RuntimeField<GenericModel, Object>> fields;
    Map<FieldId, Object> valueMap;
    TypeAdapterRegistry adapterRegistry;
}
interface PathMethod<T, R> {
    R get(T link)
    void set(T link, R value)
    R create(T link)
}
class ListPathMethod<T, R> {
    Supplier<R> supplier
    Function<T, List<R>> readMethod
    BiConsumer<T, List<R>> writeMethod
}
class RuntimeField<B, R> {
    List<PathMethod<Object, Object>> chain
    PathMethod<Object, R> lastLink
    FieldId id
    Metadata metadata
    FieldId[] siblings
    Class<R> type
    Class<?>[] genericTypes
    boolean isCodeLookup
    boolean isCodeValuable
    boolean isTransient
}
class RuntimeModel<M> {
    RuntimeFieldRegistry<M> fieldRegistry
    M model
}
class SimplePathMethod<T, R> {
    Supplier<R> supplier
    Function<T, R> readMethod
    BiConsumer<T, R> writeMethod
}
PathMethod <|.. ListPathMethod
PathMethod <|.. SimplePathMethod

GenericModel -- FieldChainBuilder
FieldChainBuilder -- RuntimeModel
FieldChainBuilder - RuntimeField
RuntimeModel -- PathMethod
```