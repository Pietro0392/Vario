/****************************************************************************
  NOME FILE   : uart.c  
  DESCRIZIONE : Driver UART
****************************************************************************/
#include <avr/io.h>
#include <avr/interrupt.h>
#include <avr/pgmspace.h>
#include "common.h"
#include "uart.h"

/****************************************************************************
   DICHIARAZIONE VARIABILI
****************************************************************************/
static volatile FIFO txfifo, rxfifo;

/****************************************************************************
   FUNZIONE   : 
   DESCRIZIONE: UART RXC interrupt. Riceve un byte e lo inserisce nel buffer rx.
				Se il buffer rx è pieno il byte è scartato.
   PARAMETRI  : N/A
   RETURN CODE: N/A
****************************************************************************/
ISR(USART_RXC_vect)
{
	U8 d, n, i;

	d = UDR;
	n = rxfifo.count;
	if(n < sizeof(rxfifo.buff)) 
	{
		rxfifo.count = ++n;
		i = rxfifo.idx_w;
		rxfifo.buff[i++] = d;
		if(i >= sizeof(rxfifo.buff)) i = 0;
		rxfifo.idx_w = i;
	}
}

/****************************************************************************
   FUNZIONE   : 
   DESCRIZIONE: UART UDRE interrupt. Preleva un byte dal buffer tx e lo trasmette.
				Se il buffer tx è vuoto disabilita interrupt di trasm.
   PARAMETRI  : 
   RETURN CODE: 
****************************************************************************/
ISR(USART_UDRE_vect)
{
	U8 n, i;

	n = txfifo.count;
	if(n) 
	{
		txfifo.count = --n;
		i = txfifo.idx_r;
		UDR = txfifo.buff[i++];
		if(i >= sizeof(txfifo.buff)) i = 0;
		txfifo.idx_r = i;
	}
	if(n == 0)
		UCSRB = BIT(RXEN)|BIT(RXCIE)|BIT(TXEN);
}

/****************************************************************************
   FUNZIONE   : uart_init
   DESCRIZIONE: Inizializza UART e svuota i buffer.
   PARAMETRI  : 
   RETURN CODE: 
****************************************************************************/
void uart_init(void)
{
	rxfifo.idx_r = 0;
	rxfifo.idx_w = 0;
	rxfifo.count = 0;
	txfifo.idx_r = 0;
	txfifo.idx_w = 0;
	txfifo.count = 0;

	UBRRL = (FOSC*1000UL)/BAUDRATE/16-1;
	UCSRB = BIT(RXEN)|BIT(RXCIE)|BIT(TXEN);
}

/****************************************************************************
   FUNZIONE   : uart_test
   DESCRIZIONE: Restituisce il numero di byte nel buffer rx.
   PARAMETRI  : 
   RETURN CODE: numero di byte presenti
****************************************************************************/
U8 uart_test (void)
{
	return rxfifo.count;
}

/****************************************************************************
   FUNZIONE   : uart_get
   DESCRIZIONE: Preleva un byte dal buffer rx. Se il buffer rx è vuoto attende
				un nuovo byte.
   PARAMETRI  : N/A
   RETURN CODE: byte ricevuto
****************************************************************************/
U8 uart_get (void)
{
	U8 d, i;

	i = rxfifo.idx_r;
	while(rxfifo.count == 0) WDR();
	d = rxfifo.buff[i++];
	cli();
	rxfifo.count--;
	sei();
	if(i >= sizeof(rxfifo.buff)) i = 0;
	rxfifo.idx_r = i;

	return d;
}

/****************************************************************************
   FUNZIONE   : uart_put
   DESCRIZIONE: Inserisce un byte nel buffer tx. Se il buffer tx è pieno attende
				il suo svuotamento.
   PARAMETRI  : data - byte da trasmettere
   RETURN CODE: N/A
****************************************************************************/
void uart_put (U8 data)
{
	U8 i;

	i = txfifo.idx_w;
	while(txfifo.count >= sizeof(txfifo.buff)) WDR();
	txfifo.buff[i++] = data;
	cli();
	txfifo.count++;
	UCSRB = BIT(RXEN)|BIT(RXCIE)|BIT(TXEN)|BIT(UDRIE);
	sei();
	if(i >= sizeof(txfifo.buff)) i = 0;
	txfifo.idx_w = i;
}

/****************************************************************************
  FUNZIONE    : uart_puts
  DESCRIZIONE : Trasmette una stringa attraverso la UART. La stringa deve 
				essere terminata con 0.
  PARAMETRI   : str - Punt. stringa da trasmettere
  RETURN CODE : 
****************************************************************************/
void uart_puts(U8 *str)
{
	while (*str != '\0')
	{
		uart_put(*str++);
	}
}

/****************************************************************************
  FUNZIONE    : uart_puts
  DESCRIZIONE : Trasmette una stringa in FLASH attraverso la UART. La stringa
				deve essere terminata con 0.
  PARAMETRI   : str - Punt. stringa da trasmettere
  RETURN CODE : N/A
****************************************************************************/
void uart_puts_P(const U8 *str)
{
	while (pgm_read_byte(str) != '\0')
	{
		uart_put(pgm_read_byte(str++));
	}
}

/****************************************************************************
  FUNZIONE    : uart_read
  DESCRIZIONE : Preleva tutti i byte contenuti nel buffer rx fino al riempimento
				del buffer utente. Funz. non sospensiva.
  PARAMETRI   : buf - Punt. buffer utente
  RETURN CODE : numero di byte ricevuti
****************************************************************************/
U16 uart_read(U8 *buf, U16 size)
{
	U8 cnt = 0;								// Numero byte letti

	while (rxfifo.count && (cnt < size))
	{
		*buf++ = uart_get();
		cnt++;
	}
	return cnt;
}

/****************************************************************************
  FUNZIONE    : uart_write
  DESCRIZIONE : Trasmette il numero specificato di byte.
  PARAMETRI   : buf - Punt. buffer utente
				cnt - Numero di byte da trasmettere 
  RETURN CODE : N/A
****************************************************************************/
void uart_write(U8 *buf, U16 cnt)
{
	while (cnt)
	{
		uart_put(*buf++);
		cnt--;
	}
}
