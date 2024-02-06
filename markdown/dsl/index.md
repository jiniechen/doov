```plantuml
hide empty member
interface DslField<T> {
    FieldId id()
    DefaultFunction<T, ? extends Metadata> getDefaultFunction()
    Metadata getMetadata()
}
class DOOV {
    {static}StepWhen when(StepCondition condition)
    {static} StepCondition alwaysTrue()
    {static}StepCondition alwaysTrue(String readable)
    {static}StepCondition alwaysFalse()
    {static}StepCondition alwaysFalse(String readable)
    {static}IntegerFunction count(StepCondition... steps)
    {static}StepCondition matchAny(StepCondition... steps)
    {static}StepCondition not(StepCondition step)
    {static}StepCondition matchAny(Stream<? extends DslField<?>> dslFields,Function<DefaultFunction<?, ?>, StepCondition> stepConditionFunction) 
    {static}StepCondition matchAll(StepCondition... steps)
    {static}StepCondition matchAll(Stream<? extends DslField<?>> dslFields,Function<DefaultFunction<?, ?>, StepCondition> stepConditionFunction)
    {static}StepCondition matchNone(StepCondition... steps)
    {static}StepCondition matchNone(Stream<? extends DslField<?>> dslFields, Function<DefaultFunction<?, ?>,StepCondition> stepConditionFunction) 
    {static}<I> SimpleStepMap<I> map(DslField<I> inFieldInfo)
    {static}<I, J> BiStepMap<I, J> map(DslField<I> inFieldInfo, DslField<J> in2FieldInfo)
    {static}NaryStepMap map(DslField<?>... inFieldInfos)
    {static}NaryStepMap map(Stream<? extends DslField<?>> inFieldInfos)
    {static}<I> StaticStepMap<I> map(Supplier<I> valueSupplier)
    {static}<I> StaticStepMap<I> map(I value)
    {static}<I, C extends Iterable<I>> IterableStepMap<I, C> mapIter(C value)
    {static}<I> IterableStepMap<I, List<I>> mapIter(I... values) 
    {static}<O> MappingRule mapNull(DslField<O> outFieldInfo) 
    {static}<O> MappingRule mapNull(MappingOutput<O> outFieldInfo)
    {static}<O> MappingRule mapNull(TagId tag)
    {static}<I> ContextawareStepMap<I> map(BiFunction<FieldModel, Context, I> valueFunction)
    {static}<I> ContextawareStepMap<I> map(MappingInput<I> input)
    {static}<I, J> BiContextawareStepMap<I, J> map(MappingInput<I> input, MappingInput<J> input2)
    {static}<I, J> BiContextawareStepMap<I, J> map(BiFunction<FieldModel, Context, I> valueFunction, BiFunction<FieldModel, Context, J> valueFunction2) 
    {static}MappingRegistry mappings(MappingRule... mappingRules)
    {static}MappingRegistry mapRange(int startInclusive, int endExclusive, Function<Integer, MappingRule> mappingRuleFunction)
    {static}<T extends DslField<?>> MappingRegistry mapFor(Stream<T> fieldStream, Function<T, MappingRule> mappingRuleFunction) 

}
```