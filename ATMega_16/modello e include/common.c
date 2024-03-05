/****************************************************************************
  NOME FILE   : common.c  
  DESCRIZIONE : Funzioni comuni
****************************************************************************/
#include "common.h"


/****************************************************************************
  FUNZIONE    : delayUs
  DESCRIZIONE : Ciclo di ritardo di N us. Tarata per Fcpu=16MHz.
  PARAMETRI   : us - tempo di attesa in us
  RETURN CODE : 
****************************************************************************/
void delayUs(U16 us) 
{
	// Una iterazione impiega 8 cicli 
    for (; us; us--)
	{
		WDR();
		NOP();
	}
} 

/****************************************************************************
  FUNZIONE    : delayMs
  DESCRIZIONE : Ciclo di ritardo di N ms.
  PARAMETRI   : ms - tempo di attesa in ms
  RETURN CODE : 
****************************************************************************/
void delayMs(U16 ms)
{
	for (; ms; ms--)
		delayUs(998);
}



