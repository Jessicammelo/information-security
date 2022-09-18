import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  public formGroup: FormGroup;

  constructor(
    protected formBuilder: FormBuilder,
    protected toastController: ToastController
  ) { }

  ngOnInit() {
    this.formGroup = this.getTextArea();
  }

  public getTextArea() {
    return this.formBuilder.group({
      textArea: [{ value: undefined, disabled: false }],
    });
  }

  public results() {
    const form = this.formGroup.get('textArea').getRawValue().toUpperCase();
    if (form.match(/\d+/g)) {
      this.formGroup.reset();
      return alert('Não pode inserir números!');
    } else {
      const results = form.split('').map(val => val.charCodeAt());
      return results;
    }
  }

  public async cypher() {
    const cypheredCharCodes = this.cypherText(this.results());
    const resultMessage = this.transformIntoLetters(cypheredCharCodes);
    const toast = await this.toastController.create({
      message: `${resultMessage} Criptografada!`,
      duration: 3500,
    });
    await toast.present();
    this.formGroup.reset();
  };

  public async deCypher() {
    const cypheredCharCodes = this.deCypherText(this.results());
    const resultMessage = this.transformIntoLetters(cypheredCharCodes);
    const toast = await this.toastController.create({
      message: `${resultMessage} Descriptografada!`,
      duration: 3500,
    });
    await toast.present();
    this.formGroup.reset();
  };

  public cypherText(textAreaArray) {
    return textAreaArray.map((charCode) => {
      if (charCode >= 88) {
        charCode -= 26;
      }
      charCode += 3;
      return charCode;
    });
  }

  public deCypherText(textAreaArray) {
    return textAreaArray.map((charCode) => {
      if (charCode <= 67) {
        charCode += 26;
      }
      charCode -= 3;
      return charCode;
    });
  }

  public transformIntoLetters(textAreaArray: number[]) {
    const value = textAreaArray.map(t => String.fromCharCode(t)).join('');
    return value.replace(/[-\/\\^$*#+?.()|[\]{}[0-9]/g, '\n');
  }
}
