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
	DDRA  = 0b00000000;	//aggiungere commenti sul singolo I/O
	PORTA = 0b11111111;
	DDRB  = 0b00000000;
	PORTB = 0b11111111;
	DDRC  = 0b00000001;
	PORTC = 0b11111110;
 	DDRD  = 0b00000000;
	PORTD = 0b11111111;

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
	char U8=0;
	while(1) 
	{
		if(!TST(PINB,4))
		{
			//U8=!U8;
				if(U8==0)
				{
					U8=1;
				}
				else{U8=0;}
					
			delayMs(50);
			
			while(!TST(PINB,4))
			{}
				
			delayMs(50);
			
					if(U8)
					{
						while(TST(PINB,4))
						{
						TGL(PORTC,0);
						delayMs(250);
						}
					}
					
					else
					{
						CLR(PORTC,0);
					}
			}
	}
}


