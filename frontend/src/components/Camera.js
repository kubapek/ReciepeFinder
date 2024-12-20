import React, { useRef, useEffect } from 'react';

const Camera = () => {
  const videoRef = useRef(null);  // Używamy useRef do przechowywania referencji do elementu video

  useEffect(() => {
    // Używamy hooka useEffect, aby załadować strumień kamery, gdy komponent zostanie zamontowany
    const getVideo = () => {
      navigator.mediaDevices.getUserMedia({ video: true })  // Prośba o dostęp do kamery
        .then((stream) => {
          let video = videoRef.current;
          if (video) {
            video.srcObject = stream;  // Przypisanie strumienia do elementu video
          }
        })
        .catch((err) => {
          console.error("Error accessing the camera: ", err);
        });
    };

    getVideo();
  }, [videoRef]);  // Dodanie videoRef do listy zależności useEffect

  return (
    <div>
      <video ref={videoRef} autoPlay playsInline></video>  
    </div>
  );
};

export default Camera;
