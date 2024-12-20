import sys
from ultralytics import YOLO

if len(sys.argv) < 3:
    print("Użycie: python3 detect.py input.mp4 output.mp4")
    sys.exit(1)

input_path = sys.argv[1]
output_path = sys.argv[2]

# Załóżmy, że posiadamy model YOLO zainstalowany lokalnie, np. YOLO('yolov8n.pt')
model = YOLO('yolov8n.pt')

# Przetwarzanie wideo:
results = model.predict(source=input_path, save=True, save_txt=False, project='/tmp', name='detected', exist_ok=True)

# W domyśle YOLO zapisze przetworzony plik do /tmp/detected/...
# Zakładamy, że nazwa wyjściowa to /tmp/detected/input.mp4 (YOLO zwykle zapisuje z oryginalną nazwą pliku)
# Możesz sprawdzić, jak YOLO nazywa wyjście - w razie potrzeby dostosować kod.

import os
detected_output = f"/tmp/detected/{os.path.basename(input_path)}"

if not os.path.exists(detected_output):
    print("Błąd: wynik nie został wygenerowany przez YOLO")
    sys.exit(1)

# Przenosimy/zmieniamy nazwę wygenerowanego pliku na docelowy
os.rename(detected_output, output_path)

print("Przetwarzanie zakończone sukcesem.")
