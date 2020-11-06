import pyqrcode
m=input("Enter the input you want to enter as msg in qrcode: ")
q=pyqrcode.create(m)
f=input("Enter the file name you want to save : ")
q.png(f,scale=10)
