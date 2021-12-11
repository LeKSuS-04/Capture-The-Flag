import tkinter as tk
import check
from PIL import ImageTk, Image
import pygame
master = tk.Tk()
pygame.mixer.init()
pygame.mixer.music.load("videoplayback.mp3")
pygame.mixer.music.play(loops=1)
def clear():
    for widget in master.winfo_children():
        widget.destroy()

def fail(t):
    t.destroy()
    f = tk.Tk()
    f.title("URGENT MESSAGE FROM COMMUNISTIC CHINA PARTY")
    f.geometry("1100x650")
    failImage = ImageTk.PhotoImage(Image.open("fail.png"))
    tk.Label(f, image=failImage).pack()
    tk.Button(f, text="Proceed", width=30, height=3, command=exit).pack()
    f.mainloop()

def failSocial():
    master.destroy()
    social = tk.Tk()
    social.title("Transaction completed")
    social.geometry("450x350")
    failImage = ImageTk.PhotoImage(Image.open("failsocial.png"))
    tk.Label(social, image=failImage).pack()
    tk.Button(social, text="Proceed", width=30, height=3, command=lambda: fail(social)).pack()
    social.mainloop()

def finalize(w):
    w.destroy()
    clear()
    guard = ImageTk.PhotoImage(Image.open("final.png"))
    master.geometry("1200x800")
    label = tk.Label(master, image=guard)
    label.image = guard
    label.pack()
    tk.Label(master, text="THANK YOU FOR PARTICIPATING IN OUR SURVEY! ", font=("Courier", 15)).pack()
    tk.Label(master, text="GLORY TO GREAT CHINA!", font=("Courier", 15)).pack()
    tk.Button(master, text="GLORY TO GREAT CHINA", command=exit).pack()


def gloriousSuccess():
    win = ImageTk.PhotoImage(Image.open("gloriousVictory.png"))
    winTk = tk.Toplevel()
    winTk.title("Transaction completed")
    tk.Label(winTk, image=win).pack()
    tk.Button(winTk, text="Praise the Great XI and go to work", command=lambda: finalize(winTk)).pack()
    winTk.mainloop()


def checkFlag(text):
    input = text.get("1.0","end-1c")
    print(bytes(input, 'ascii'))
    import check
    res = check.checkFlag(input)
    if res:
        gloriousSuccess()
    else:
        failSocial()


def finalQuestion(w):
    w.destroy()
    clear()
    guard = ImageTk.PhotoImage(Image.open("Guard.png"))
    master.geometry("1200x400")
    label = tk.Label(master, image=guard)
    label.image = guard
    label.pack()
    tk.Label(master, text="LAST QUESTION! PLEASE PROVIDE SECRET GREETING OF CHINESE PEOPLE!", font=("Courier", 20)).pack()
    text = tk.Text(master, height=1, width=40)
    text.pack()
    tk.Button(master, text="Check secret greeting", command=lambda: checkFlag(text)).pack()


def thirdQuestion(w):
    w.destroy()
    clear()
    master.geometry("800x350")
    tk.Label(text="What happened in Tiananmen Square in 1989?").pack()
    tk.Button(text="Protests of students", width=50, height=3, command=failSocial).pack()
    tk.Button(text="A lot of people died", width=50, height=3, command=failSocial).pack()
    tk.Button(text="Horrible massacre", width=50, height=3, command=failSocial).pack()
    tk.Button(text="Absolutely nothing <---", width=50, height=3, command=lambda: success(finalQuestion)).pack()

def secondQuestion(w):
    w.destroy()
    clear()
    master.geometry("800x350")
    tk.Label(text="Is Thaiwan a country?").pack()
    tk.Button(text="No", width=50, height=3, command=lambda: success(thirdQuestion)).pack()
    tk.Button(text="No", width=50, height=3, command=lambda: success(thirdQuestion)).pack()
    tk.Button(text="Yes", width=50, height=3, command=failSocial).pack()
    tk.Button(text="No", width=50, height=3, command=lambda: success(thirdQuestion)).pack()

def success(f):
    win = ImageTk.PhotoImage(Image.open("pluscredits.jpg"))
    winTk = tk.Toplevel()
    winTk.title("Transaction completed")
    tk.Label(winTk, image=win).pack()
    tk.Button(winTk, text="Praise the Great XI and continue", command= lambda: f(winTk)).pack()
    winTk.mainloop()



def firstQuestion():
    clear()
    master.geometry("800x350")
    tk.Label(text="Which is the best country in the universe?").pack()
    tk.Button(text="Big Mac country", width=50, height=3, command=failSocial).pack()
    tk.Button(text="Vodka country", width=50, height=3, command=failSocial).pack()
    tk.Button(text="THE GREATEST CHINA", width=50, height=3, command=lambda: success(secondQuestion)).pack()
    tk.Button(text="Nazi Adolf country (very bad)", width=50, height=3, command=failSocial).pack()


start = ImageTk.PhotoImage(Image.open("start.jpg"), size="1024x600")
panel=tk.Label(master, image=start)
panel.pack(side="top", fill = "both", expand="yes")


master.title("Great China citizen survey GREAT XI NUMBER ONE CHINA GO GO GO!!!!")
master.geometry("1024x768")
launch = tk.Button(text="Check how good citizen you are", width=25, height=2, command=firstQuestion)
launch.pack()
master.mainloop()
