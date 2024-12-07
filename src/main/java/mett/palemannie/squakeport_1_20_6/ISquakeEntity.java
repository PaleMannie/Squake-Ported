package mett.palemannie.squakeport_1_20_6;

public interface ISquakeEntity {
    int getDisabledMovementTicks_Squake();

    void setDisabledMovementTicks_Squake(int amt);

    default boolean shouldReturnMovement_Squake() {
        return getDisabledMovementTicks_Squake() > 0;
    }
}
