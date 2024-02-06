```plantuml
hide empty member
interface DSLBuilder {

}
abstract class AbstractDSLBuilder {

}
interface BiTypeConverter<I, J, O> {
    O convert(FieldModel fieldModel, Context context, I in, J in2)
}
interface Function3<T, U, V, R> {
    R apply(T t, U u, V v)
}
interface Function4<T1, T2, T3, T4, R> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4)
}
interface Function5<T1, T2, T3, T4, T5, R> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5)
}
interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v)
}
interface MappingRule {
    boolean validate(FieldModel inModel, FieldModel outModel)AbstractDSLBuilder
    R apply(T t, U u, V v)
    Context executeOn(FieldModel inModel, FieldModel outModel)
    Context executeOn(FieldModel model)
    <C extends Context> C executeOn(FieldModel inModel, FieldModel outModel, C context)
    <C extends Context> C executeOn(FieldModel model, C context)
}
interface ConditionalMappingRule {
    ConditionalMappingRule otherwise(MappingRule... mappingRule)
}
interface StepCondition {
    BiPredicate<FieldModel, Context> predicate()
}
interface StepWhen{
    StepCondition stepCondition()
    ValidationRule validate()
    Result executeOn(FieldModel model)
    ConditionalMappingRule then(MappingRule... mapRule)
}
interface ValidationRule {
    StepWhen getStepWhen()
    ValidationRule withShortCircuit(boolean shortCircuit)
    Result executeOn(FieldModel model)
    Result executeOn(FieldModel model, Context context)
}
interface Result {
    boolean value()
    Context getContext()
}
MappingRule ..|> DSLBuilder
DSLBuilder <|. StepCondition
DSLBuilder <|.. StepWhen
DSLBuilder <|.. AbstractDSLBuilder
MappingRule <|. ConditionalMappingRule
DSLBuilder <|.. ValidationRule
AbstractDSLBuilder -[thickness=0]- BiTypeConverter
BiTypeConverter -[thickness=0]- Function3
Function3 -[thickness=0]- Function4
Function4 -[thickness=0]- Function5
Function5 -[thickness=0]- TriConsumer
StepCondition --* StepWhen
StepWhen *--* ValidationRule
ValidationRule -- Result
```