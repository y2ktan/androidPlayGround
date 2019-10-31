package com.androidPlayground.simulatorradiogateway.usecase;

import com.google.gson.Gson;
import com.androidPlayground.simulatorradiogateway.gateway.interfaces.SimulatorInstanceLocator;

public class SimulationPttCall implements SimulatorUseCase {
    public static final String action = "com.androidPlayground";
    private final Gson gson = new Gson();

    public SimulationPttCall() {

    }

    @Override
    public void execute(String data) {
        PttCallData pttCallData = gson.fromJson(data, PttCallData.class);
        SimulatorInstanceLocator.getSimulatorPttCall().onConventionalPttCall(
                pttCallData.callModeOption,
                pttCallData.pttIdOrAliasLocalAddress,
                pttCallData.pttIdOrAliasFarAddress,
                pttCallData.groupIdOrAlias,
                pttCallData.radioCallStatus
        );
    }


    static class PttCallData {
        @JsonRequired
        String callModeOption;
        @JsonRequired
        String pttIdOrAliasLocalAddress;
        @JsonRequired
        String pttIdOrAliasFarAddress;
        @JsonRequired
        String groupIdOrAlias;
        @JsonRequired
        String radioCallStatus;
    }
}