import provaDFAes3 as utils
import random, os

def main():
   test(1000000)
#    manualTest()

def test(n):
    non_accettati = set()
    percorsoFile = os.path.join(os.path.expanduser("~"), "Desktop", "esito.txt")
    with open(percorsoFile,"w") as file:
        file.write("Stringhe generate:\n\n")
        counter = 0
        for i in range (n):
           num_casuale = random.randint(8,n)
           num_casuale_binario = bin(num_casuale)[2:]
           file.write(num_casuale_binario + "\n")
           if num_casuale_binario[-3]==utils.zero and not (utils.deltaHat(utils.q0,num_casuale_binario) in utils.F):
                print(f"{num_casuale_binario} non e' stato accettato")
                non_accettati.add(num_casuale_binario + "\n")
           else: counter+=1
        print(f"\nnumero di accettati: {counter} su {n}\n")
        file.write(f"\nnumero di accettati: {counter} su {n}\n")
        if not non_accettati:
            print("\nIl test si e' concluso correttamente,")
            file.write("\nIl test si e' concluso correttamente\n")
        else:
            file.write("\nL'automa ha rifiutato i seguenti:\n\n")
            file.writelines(non_accettati)
        print(f"\npiu' informazioni nel file sul desktop: {percorsoFile}")

def manualTest():
     while True:
          w = input("dammi una stringa: (stop per uscire oppure Cntrl+C) ")
          if utils.deltaHat(utils.q0,w) in utils.F:
               print("Stringa accettata")
          else: print("Stringa rifiutata")
main()