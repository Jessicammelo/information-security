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
      textKey: [{ value: undefined, disabled: false }]
    });
  }


  public processarChave(results, resultKey) {
    const keyNumberFinal = [];

    if (resultKey.length <= results.length) {
      for (var i = 0; i < results.length; i++) {
        keyNumberFinal[i] = resultKey[i % resultKey.length];
      }
    } else {
      alert("Erro! Informe uma chave menor ou um texto maior.");
    }
    return keyNumberFinal;
  }

  public async cypher() {
    const cypheredCharCodes = this.cypherText();
    const resultMessage = cypheredCharCodes;
    const toast = await this.toastController.create({
      message: `${resultMessage} Criptografada!`,
      duration: 3500,
    });
    await toast.present();
    this.formGroup.reset();
  };

  public async deCypher() {
    const cypheredCharCodes = this.deCypherText();
    const resultMessage = cypheredCharCodes;
    const toast = await this.toastController.create({
      message: `${resultMessage} Descriptografada!`,
      duration: 3500,
    });
    await toast.present();
    this.formGroup.reset();
  };

  public cypherText() {
    const inputText = this.formGroup.get('textArea').getRawValue().toUpperCase();
    const inputKey = this.formGroup.get('textKey').getRawValue().toUpperCase();
    if (inputText.match(/\d+/g) || inputKey.match(/\d+/g)) {
      this.formGroup.reset();
      return alert('Não pode inserir números!');
    } else {
      const results = inputText.split('').map(val => val.charCodeAt());
      const resultKey = inputKey.split('').map(val => val.charCodeAt());

      let keyNumberFinal = this.processarChave(results, resultKey);

      let resultFinal = '';
      let resultNumeric = [];

      for (let i = 0; i < results.length; i++) {
        if (results === 69) {
          resultNumeric[i] = (results[i] + keyNumberFinal[i]) % 26;
        } else {
          resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26) < 0 ?
            resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26) + 26 :
            resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26);
        }

        resultFinal += String.fromCharCode(resultNumeric[i] + 65);
      }
      return resultFinal;
    }
  }

  public deCypherText() {
    const inputText = this.formGroup.get('textArea').getRawValue().toUpperCase();
    const inputKey = this.formGroup.get('textKey').getRawValue().toUpperCase();
    if (inputText.match(/\d+/g) || inputKey.match(/\d+/g)) {
      this.formGroup.reset();
      return alert('Não pode inserir números!');
    } else {
      const results = inputText.split('').map(val => val.charCodeAt());
      const resultKey = inputKey.split('').map(val => val.charCodeAt());

      let keyNumberFinal = this.processarChave(results, resultKey);

      let resultFinal = '';
      let resultNumeric = [];

      for (let i = 0; i < results.length; i++) {
        if (results === 69) {
          resultNumeric[i] = (results[i] + keyNumberFinal[i]) % 26;
        } else {
          resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26) < 0 ?
            resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26) + 26 :
            resultNumeric[i] = ((results[i] - keyNumberFinal[i]) % 26);
        }

        resultFinal += String.fromCharCode(resultNumeric[i] + 65);
      }
      return resultFinal;
    }
  }
}
