```plantuml
hide empty member
class DefaultContext  {
    Metadata rootMetadata
    List<Metadata> evalTrue
    List<Metadata> evalFalse
    Map<FieldId, Object> values
    boolean shortCircuit
}
class DefaultFunction<T, M extends Metadata>{
    M metadata
    BiFunction<FieldModel, Context, Optional<T>> function
}
class DefaultResult {
    boolean validated
    Context context
}
class DefaultStepCondition {
    PredicateMetadata metadata
    BiPredicate<FieldModel, Context> predicate
}
class DefaultStepWhen {
    WhenMetadata metadata
    StepCondition stepCondition
}
class DefaultValidationRule {
    Metadata metadata
    StepWhen stepWhen
    boolean shortCircuit
}
class LeafStepCondition<N> {

}
class LogicalBinaryCondition {

}
class LogicalNaryCondition {

}
class LogicalUnaryCondition {

}

DefaultStepCondition <|- LeafStepCondition
DefaultStepCondition <|-- LogicalNaryCondition
DefaultStepCondition <|-- LogicalUnaryCondition

DefaultFunction --[thickness=0] DefaultContext
DefaultContext -[thickness=0]- DefaultResult
DefaultContext -[thickness=0]- DefaultStepWhen
DefaultContext -[thickness=0]- DefaultValidationRule
LogicalBinaryCondition -[thickness=0]- DefaultContext
DefaultStepWhen -[thickness=0]- DefaultStepCondition
```