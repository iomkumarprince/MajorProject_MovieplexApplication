import { AbstractControl, FormControl } from "@angular/forms";
export class CustomValidator {
    static mustMatchValidator(ab: AbstractControl) {
        if (ab.get('password')?.value == ab.get('confirmPassword')?.value)
            return null;
        else
            return { mustMatchValidator: true };
    }
}