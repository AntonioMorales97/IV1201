import React from 'react';
import { useTranslation } from 'react-i18next';
import { Container, ListGroup } from 'reactstrap';
import ApplicationItem from './application-item';

import './applications.css';

const ApplicationsView = () => {
  const { t } = useTranslation();

  return (
    <Container className='applications'>
      <h1>{t('applications')}</h1>
      <ListGroup>
        <ApplicationItem />
        <ApplicationItem />
        <ApplicationItem />
        <ApplicationItem />
      </ListGroup>
    </Container>
  );
};

export default ApplicationsView;
