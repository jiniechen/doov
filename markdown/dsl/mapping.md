```plantuml
hide empty member
class ConverterInput<S, T> {
    MappingInput< S> sourceInput
    private final TypeConverter<S, T> typeConverter
    private final MappingInputMetadata metadata
}
class BiConverterInput<U, S, T> {
    MappingInputMetadata metadata
    MappingInput< U> mappingInput1
    MappingInput< S> mappingInput2
    BiTypeConverter<U, S, T> converter
}
class ConsumerOutput<T> {
    TriConsumer<FieldModel, Context, T> outputFunction
    MappingMetadata metadata
}
class DefaultConditionalMappingRule {
    StepWhen stepWhen
    ValidationRule validationRule
    MappingRegistry mappingRules
    MappingRegistry elseMappingRules
}
class DefaultMappingRule<T> {
    MappingInput<T> input
    MappingOutput<T> output
    MappingRuleMetadata metadata
}
class FieldInput<T> {
    DslField<T> field
    MappingMetadata metadata
}
class FieldOutput<T> {
    DslField<T> field
    MappingMetadata metadata
}
class FunctionInput<T>  {
    BiFunction<FieldModel, Context, T> valueFunction
    MappingMetadata metadata
}
class StaticInput<T> {
    Supplier<T> valueSupplier;
    StaticMetadata<T> metadata
}

MappingInput <|.. ConverterInput
MappingInput <|. BiConverterInput
MappingOutput <|.. ConsumerOutput
DefaultMappingRule *-- MappingInput
DefaultMappingRule *-- MappingOutput
MappingRule <|. ConditionMappingRule
MappingRule <|.. DefaultMappingRule
ConditionMappingRule <|.. DefaultConditionalMappingRule
FieldInput ..|> MappingInput
MappingOutput <|. FieldOutput
MappingInput <|... FunctionInput
MappingInput <|... StaticInput
```