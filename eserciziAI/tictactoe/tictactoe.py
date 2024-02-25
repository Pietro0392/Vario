"""
Tic Tac Toe Player
"""

import math
import copy

X = "X"
O = "O"
EMPTY = None

def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]

def player(board):
    count_X = sum(row.count(X) for row in board)
    count_O = sum(row.count(O) for row in board)

    # Se ci sono più X che O, è il turno del giocatore O
    if count_X > count_O:
        return O
    # Se ci sono lo stesso numero di X e O, è il turno del giocatore X
    else:
        return 'X'


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    freeCells = set()

    for i in range (3):                         # ... in range (len(board))
        for j in range (3):                     # ... in range (len(board[i]))
            if board[i][j] == EMPTY:
                freeCells.add((i,j,))

    return freeCells



def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """
    boardCopy = copy.deepcopy(board)
    boardCopy[action[0]][action[1]] = player(board)
    return boardCopy


def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    for row in board:
        if row[0] == row[1] == row[2] and row[0] is not EMPTY:
            return row[0]

    for col in range(3):
        if board[0][col] == board[1][col] == board[2][col] and board[0][col] is not EMPTY:
            return board[0][col]

    if board[0][0] == board[1][1] == board[2][2] and board[0][0] is not EMPTY:
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] is not EMPTY:
        return board[0][2]

    return None

def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    Outcome = winner(board)
    if Outcome != None or actions(board) == set():
        return True
    else: return False


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    outcome = winner(board)
    # ASSUMING utility will only be called on a board if terminal(board) is True.
    return 1 if outcome == X else -1 if outcome == O else 0

def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """
    def Max_Value(board):
        if terminal(board):
            return utility(board)
        value = float("-inf")
        for action in actions(board):
            value = max(Max_Value(result(board,action)),value)
        return value  

    def Min_Value(board):
        if terminal(board):
            return utility(board)
        value = float("+inf")
        for action in actions(board):
            value = min(Max_Value(result(board,action)),value)
        return value
    
    if terminal(board):
        return None

    player_turn = player(board)

    if player_turn == X:
        v = float('-inf')
        best_move = None
        for action in actions(board):
            new_v = Min_Value(result(board, action))
            if new_v > v:
                v = new_v
                best_move = action
    else:
        v = float('inf')
        best_move = None
        for action in actions(board):
            new_v = Max_Value(result(board, action))
            if new_v < v:
                v = new_v
                best_move = action

    return best_move