package com.warehouse.demo.utility;


import com.warehouse.demo.model.ModelOfPiano;
import com.warehouse.demo.model.request.ModelPianoRequest;
import org.springframework.stereotype.Component;

@Component
public class ModelChecker {


    public ModelOfPiano modelChecker(ModelPianoRequest model) {
        ModelOfPiano modelOfPiano;
        switch (model) {
            case A:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_A_188;
                break;
            case B:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211;
                break;
            case C:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_C_227;
                break;
            case D:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_D_274;
                break;
            case M:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_M_170;
                break;
            case O:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_O_180;
                break;
            case S:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_S_155;
                break;
            case K:
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_K_132;
                break;
            case V:
                modelOfPiano = ModelOfPiano.UPRIGHT_PIANO_V_125;
                break;
            default:
                modelOfPiano = ModelOfPiano.GRAND_PIANO_B_211;
                break;
        }
        return modelOfPiano;
    }

}
