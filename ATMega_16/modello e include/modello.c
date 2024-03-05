/*********************************************
  NOME FILE   : ___.c
  DESCRIZIONE : 
  MCU	      : Atmel ATmega16 @ 8MHz
  COMPILATORE : WinAVR-20070525 (GCC 4.1.2)
  AUTORE      : 
  VERSIONE    : 1.0
*********************************************/
#include "common.h"

/*********************************************
   FUNZIONE   : mcu_setup
   DESCRIZIONE: Inizializzazione periferiche MCU.
   PARAMETRI  : nessuno
   RETURN CODE: nessuno
*********************************************/
void mcu_setup(void)
{
	// Configurazione porte
	DDRA  = 0b___;	//aggiungere commenti sul singolo I/O
	PORTA = 0b___;
	DDRB  = 0b___;
	PORTB = 0b___;
	DDRC  = 0b___;
	PORTC = 0b___;
	DDRD  = 0b___;
	PORTD = 0b___;

	// Configurazione periferiche utilizzate
	


	// watchdog
	wdt_reset();                   
	wdt_disable();        //disabilita wdt

	// interrupt
	SEI();                //abilita interruzioni
}

/*********************************************
   FUNZIONE   : main
   DESCRIZIONE: Main loop
   PARAMETRI  : nessuno
   RETURN CODE: ignorato
*********************************************/
int main(void)
{
	mcu_setup();
	while(1) 
	{
		//corpo del programma
	}
}

