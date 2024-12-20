import React, { useState, useEffect } from 'react';

const VideoPlayer = () => {
  const [videoUrl, setVideoUrl] = useState(null);

  useEffect(() => {
    const fetchVideo = async () => {
      try {
        const response = await fetch('/api/get-video');
        if (response.ok) {
          const blob = await response.blob();
          setVideoUrl(URL.createObjectURL(blob));
        } else {
          console.error('Nie udało się pobrać wideo');
        }
      } catch (error) {
        console.error('Błąd:', error);
      }
    };

    fetchVideo();
  }, []);

  return (
    <div className="video-player">
      <h2>Odtwarzacz wideo</h2>
      {videoUrl ? (
        <video 
          src={videoUrl} 
          controls 
          width="640" 
          height="360"
        >
          Twoja przeglądarka nie obsługuje wideo.
        </video>
      ) : (
        <p>Ładowanie wideo...</p>
      )}
    </div>
  );
};

export default VideoPlayer;