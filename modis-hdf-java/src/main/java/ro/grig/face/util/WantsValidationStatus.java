package ro.grig.face.util;

/**
 * Preluat de pe web pentru validare in jdialog
 * 
 * @author grig
 *
 */
public interface WantsValidationStatus {

   void validateFailed(); // Called when a component has failed validation.

   void validatePassed(); // Called when a component has passed validation.
}
