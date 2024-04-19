import React, { useState, useEffect } from 'react';

interface Author {
  name: string;
  email: string;
  github: string;
}

interface Repository {
  type: string;
  url: string;
}

interface AboutData {
  properties: {
    title?: string;
    version?: string;
    description?: string;
    authors?: Author[];
    repository?: Repository;
  };
}

export function About() {
  const [showProperties, setShowProperties] = useState(false);
  const [aboutData, setAboutData] = useState<AboutData>({ properties: {} });

  const fetchAboutData = () => {
    fetch('http://localhost:8081/api/home', {
      method: 'GET',
    })
      .then((response) => response.json())
      .then((data: AboutData) => {
        setAboutData(data);
        setShowProperties(true);
      })
      .catch((error) => {
        console.error('Error fetching about data:', error);
      });
  };

  // useEffect hook to fetch data when the component mounts
  useEffect(() => {
    fetchAboutData();
  }, []); // The empty dependency array ensures that the effect runs once when the component mounts

  return (
    <div>
      <h1>About</h1>
      {showProperties && (
        <ul>
          {Object.entries(aboutData.properties).map(([key, value]) => (
            <li key={key}>
              {`${key}: `}
              {key === 'authors'
                ? ((value as Author[]).map((author, index) => (
                    <React.Fragment key={index}>
                      {Object.entries(author).map(([authorKey, authorValue]) => (
                        <li key={authorKey}>
                          {`${authorKey}: ${authorValue}`}
                        </li>
                      ))}
                    </React.Fragment>
                  )) as React.ReactNode[])
                : key === 'repository'
                ? <a href={(value as Repository).url}>{(value as Repository).url}</a>
                : value as React.ReactNode}
            </li>
          )) as React.ReactNode[]}
        </ul>
      )}
    </div>
  );
}
