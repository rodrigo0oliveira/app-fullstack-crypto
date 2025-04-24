import { Component, OnInit } from '@angular/core';
import { MenuBarComponent } from "../../components/menu-bar/menu-bar.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { CryptoData } from '../../entities/CryptoData';

@Component({
  selector: 'app-cotacao',
  imports: [MenuBarComponent,CommonModule],
  templateUrl: './cotacao.component.html',
  styleUrl: './cotacao.component.css'
})
export class CotacaoComponent implements OnInit{

  cryptoList:CryptoData[] = [];

  constructor(private httpClient:HttpClient){

  }

  pairs = [
    'BTC-USD',
    'ETH-USD',
    'USDT-USD',
    'SOL-USD',
    'ADA-USD',
    'XRP-USD',
    'DOGE-USD',
    'AVAX-USD',
    'MATIC-USD',
    'LTC-USD'
  ];
  

  ngOnInit(): void {
   
    this.pairs.forEach(pair=> {
      this.httpClient.get<any>(`https://api.coinbase.com/v2/prices/${pair}/spot`)
      .subscribe(res=>{
        this.cryptoList.push({
          cryptoName:res.data.base,
          cryptoCurrency:res.data.currency,
          cryptoPrice:res.data.amount
        });
      })

    });
  }
}
