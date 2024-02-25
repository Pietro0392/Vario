q0 = "q0"
q1 = "q1"
q2 = "q2"
q3 = "q3"
q4 = "q4"
q5 = "q5"
q6 = "q6"
q7 = "q7"

epsilon = ""
zero = "0"
uno = "1"

Q = {q0,q1,q2,q3,q4,q5,q6,q7}
Sigma = {zero,uno}
F = {q4,q5,q6,q7}

def delta(q,a):
    if q not in Q or a not in Sigma:
        raise ValueError("Input non validi")
    
    # if q==q?:
    #     if a==zero:
    #         return ?
    #     else: return ?

    if q==q0:
        if a==zero:
            return q1
        else: return q0
    
    if q==q1:
        if a==zero:
            return q2
        else: return q3
    
    if q==q2:
        if a==zero:
            return q4
        else: return q6
    
    if q==q3:
        if a==zero:
            return q7
        else: return q5

    if q==q4:
        if a==zero:
            return q4
        else: return q6

    if q==q5:
        if a==zero:
            return q1
        else: return q0
    
    if q==q6:
        if a==zero:
            return q7
        else: return q5

    if q==q7:
        if a==zero:
            return q2
        else: return q3

def deltaHat(q,w):
    if w == epsilon:
        return q
    else:
        v = w[:-1]
        a = w[-1]
        return delta(deltaHat(q,v),a)