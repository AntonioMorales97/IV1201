import React from 'react';
import { useTranslation } from 'react-i18next';
import { Container, ListGroup } from 'reactstrap';
import ApplicationItem from './application-item';

import './applications.css';

const ApplicationsView = props => {
  const { t } = useTranslation();
  const { applications } = props;
  return (
    <Container className='applications'>
      <h1>{t('applications')}</h1>
      <ListGroup>
        {applications.length > 0 ? (
          applications.map(application => (
            <ApplicationItem key={application.id} application={application} />
          ))
        ) : (
          <h4>{t('no_applications')}</h4>
        )}
      </ListGroup>
    </Container>
  );
};

export default ApplicationsView;
