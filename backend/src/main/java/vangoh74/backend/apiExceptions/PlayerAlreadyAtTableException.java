package vangoh74.backend.apiExceptions;

public class PlayerAlreadyAtTableException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Player already at table or Table has no free seat!!";
    }

}
