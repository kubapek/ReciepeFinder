import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const FileUpload = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const navigate = useNavigate();

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
  };

  const handleFileUpload = async () => {
    if (!selectedFile) {
      alert('Proszę wybrać plik');
      return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);

    try {
      const response = await fetch('/api/upload', {
        method: 'POST',
        body: formData
      });

      if (response.ok) {
        // Przejście do strony z odtwarzaczem wideo po udanym przesłaniu
        navigate('/video-player');
      } else {
        alert('Błąd przesyłania pliku');
      }
    } catch (error) {
      console.error('Błąd:', error);
      alert('Nie udało się przesłać pliku');
    }
  };

  return (
    <div className="file-upload">
      <h2>Prześlij plik MP4</h2>
      <input 
        type="file" 
        accept=".mp4" 
        onChange={handleFileChange}
      />
      <button 
        onClick={handleFileUpload}
        disabled={!selectedFile}
      >
        Wyślij plik
      </button>
    </div>
  );
};

export default FileUpload;