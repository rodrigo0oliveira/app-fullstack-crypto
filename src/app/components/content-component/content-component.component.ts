import { Component } from '@angular/core';

@Component({
  selector: 'app-content-component',
  imports: [],
  templateUrl: './content-component.component.html',
  styleUrl: './content-component.component.css'
})
export class ContentComponent {

  photo:string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC5OpPv5WWgtag_RfgChVEuQubbQLfh5qUfA&s";
  title:string = "Binance e Coinbase movimentam centenas de milhões para Wintermute!"
  description = "A Binance, exchange offshore, continua enviando centenas de milhões em ativos para a Wintermute, um dos maiores market makers do mercado.Enquanto isso, a Coinbase, exchange regulada nos EUA, também transfere centenas de milhões em stablecoins para a mesma Wintermute, aparentemente para absorver a pressão de venda.Tudo indica ser uma manipulação de liquidez. Os grandes players absorvem vendas para estabilizar preços e se posicionar estrategicamente para 2025.";
}
