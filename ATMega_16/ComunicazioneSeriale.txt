/*********************************************
  NOME FILE   : ___.c
  DESCRIZIONE : 
  MCU	      : Atmel ATmega16 @ 8MHz
  COMPILATORE : WinAVR-20070525 (GCC 4.1.2)
  AUTORE      : 
  VERSIONE    : 1.0
*********************************************/
#include "common.h"
#include "lcd.h"
#include "timers.h"
#include "uart.h"
#include <stdio.h>





/***************
   FUNZIONE   : mcu_setup
   DESCRIZIONE: Inizializzazione periferiche MCU.
   PARAMETRI  : nessuno
   RETURN CODE: nessuno
***************/
void mcu_setup(void)
{
	// Configurazione porte
	DDRA  = 0b11110000;	//aggiungere commenti sul singolo I/O
	PORTA = 0b00001111;
	DDRB  = 0b00000110;
	PORTB = 0b11111001;
	DDRC  = 0b10001111;	 //4 porte marcia
	PORTC = 0b01110000;
	DDRD  = 0b01110010;
	PORTD = 0b10001101;

    TCCR0=0b00001011;  //prerscaler=64
    OCR0=124;
    SET(TIMSK,OCIE0);
	// Configurazione periferiche utilizzate
		
	TCCR1A = 0b10000001; //mode=5
	TCCR1B = 0b00001001; //N=1
	
	MCUCR=0b00000011;
	SET(GICR,INT0);

	
	
	
	
	
	
	LCD_init();
	LCD_clear();
	uart_init();



	 // watchdog
	wdt_reset();                   
	wdt_disable();        //disabilita wdt

	// interrupt
	SEI();                //abilita interruzioni
}




/***************
   FUNZIONE   : main
   DESCRIZIONE: Main loop
   PARAMETRI  : nessuno
   RETURN CODE: ignorato
***************/

int main(void)
{																																																																															
																																																																																

	mcu_setup();
	
	U8 a;
	//U8 b;
	
	while(1) 
	{
		if (uart_test())
		{
			a=uart_get();
			//b=a+1;
			a++;
			uart_put(a);
			
			
		}
		
		

	}
}