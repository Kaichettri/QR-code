import QRcode_reader
m=input("Enter the data in QR code: ")
a=QRcode_reader.do_decode()
print(a)
if m==a:
    print("Authentication approved")
else: print("Nothing")    
