from pyzbar.pyzbar import decode
from PIL import Image
def do_decode():
    f=input("Enter the QR code image file: ")
    d=decode(Image.open(f))
    return d[0].data.decode('ascii')


