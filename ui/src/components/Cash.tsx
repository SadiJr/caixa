import {
    Avatar,
    Box,
    Button,
    Container,
    CssBaseline,
    Grid,
    TextField,
    Typography,
    Menu,
    MenuItem
  } from "@mui/material";
import { LockOutlined } from "@mui/icons-material";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN_NAME } from "../constants/apiConstants";
import * as React from 'react';

const Cash = () => {
  const months = {
    "Janeiro": 1,
    "Fevereiro": 2,
    "Março": 3,
    "Abril": 4,
    "Maio": 5,
    "Junho": 6,
    "Julho": 7,
    "Agosto": 8,
    "Setembro": 9,
    "Outubro": 10,
    "Novembro": 11,
    "Dezembro": 12
  };

    const navigate = useNavigate();
    const [year, setYear] = useState(null);
    const [month, setMonth] = useState(null);
    const [cashDeskId, setCashDeskId] = useState(null);
    let cashDesks: string[] = [];


    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const [anchorEf, setAnchorEf] = React.useState<null | HTMLElement>(null);
    const openMonth = Boolean(anchorEf);

    const [anchorEc, setAnchorEc] = React.useState<null | HTMLElement>(null);
    const openDesk = Boolean(anchorEc);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEl(event.currentTarget);
    };

    const handleClickMonth = (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEf(event.currentTarget);
    };

    const handleClickDesk = async (event: React.MouseEvent<HTMLButtonElement>) => {
      setAnchorEc(event.currentTarget);
      
      var token = localStorage.getItem(ACCESS_TOKEN_NAME);

      await axios.get(API_BASE_URL + '/api/cash/findAll', {
        headers: {
          'Authorization': 'Bearer ' + token
        }
      }).then(
        function (response) {
            if(response.status === 200) {
              var si = response.data.length;
              for (let desk = 0; desk < si; desk++) {
                cashDesks.push(response.data[desk].description);
              }
            } else {
                console.log("Some error ocurred");
            }
        }
      ).catch(err => {
        // Handle errors
        console.error(err);
      });

      console.log('sadi');
      console.debug(cashDesks.at(0));
      console.debug(cashDesks.pop());
    };

    const handleClose = () => {
      setAnchorEl(null);
    };

    const handleCloseMonth = () => {
      setAnchorEf(null);
    };

    const handleCloseDesk = () => {
      setAnchorEc(null);
    };

    const handleYear = (event: React.MouseEvent<HTMLButtonElement>) => {
      handleClose();
      setYear(event.currentTarget.textContent);
    };

    const handleMonth = (event: React.MouseEvent<HTMLButtonElement>) => {
      setMonth(months[event.currentTarget.textContent]);
      handleCloseMonth();
    };

    const handleDesk = (event: React.MouseEvent<HTMLButtonElement>) => {
      console.log(event);
      handleCloseDesk();
    };

    return (
      <>
        <Container maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              mt: 20,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "primary.light" }}>
              <LockOutlined />
            </Avatar>
            <Typography variant="h5">Consultar caixa</Typography>
            <Button
              id="basic-button"
              aria-controls={open ? 'basic-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={open ? 'true' : undefined}
              onClick={handleClick}
            >
              Ano
            </Button>
            
            <Menu
              id="basic-menu"
              anchorEl={anchorEl}
              open={open}
              onClose={handleClose}
              MenuListProps={{
                'aria-labelledby': 'basic-button',
              }}
            >
              <MenuItem onClick={handleYear}>2022</MenuItem>
              <MenuItem onClick={handleYear}>2023</MenuItem>
              <MenuItem onClick={handleYear}>2024</MenuItem>
            </Menu>
            <Button
              id="month-button"
              aria-controls={openMonth ? 'month-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={openMonth ? 'true' : undefined}
              onClick={handleClickMonth}
            >
              Mês
            </Button>
            
            <Menu
              id="month-menu"
              anchorEl={anchorEf}
              open={openMonth}
              onClose={handleCloseMonth}
              MenuListProps={{
                'aria-labelledby': 'month-button',
              }}
            >
              <MenuItem onClick={handleMonth}>Janeiro</MenuItem>
              <MenuItem onClick={handleMonth}>Fevereiro</MenuItem>
              <MenuItem onClick={handleMonth}>Março</MenuItem>
              <MenuItem onClick={handleMonth}>Abril</MenuItem>
              <MenuItem onClick={handleMonth}>Maio</MenuItem>
              <MenuItem onClick={handleMonth}>Junho</MenuItem>
              <MenuItem onClick={handleMonth}>Julho</MenuItem>
              <MenuItem onClick={handleMonth}>Agosto</MenuItem>
              <MenuItem onClick={handleMonth}>Setembro</MenuItem>
              <MenuItem onClick={handleMonth}>Outubro</MenuItem>
              <MenuItem onClick={handleMonth}>Novembro</MenuItem>
              <MenuItem onClick={handleMonth}>Dezembro</MenuItem>
            </Menu>

            <Button
              id="desk-button"
              aria-controls={openDesk ? 'desk-menu' : undefined}
              aria-haspopup="true"
              aria-expanded={openDesk ? 'true' : undefined}
              onClick={handleClickDesk}
            >
              Caixa
            </Button>
            
            <Menu
              id="desk-menu"
              anchorEl={anchorEc}
              open={openDesk}
              onClose={handleCloseDesk}
              MenuListProps={{
                'aria-labelledby': 'desk-button',
              }}
            >
              <MenuItem onClick={handleDesk}>Dezembro</MenuItem>
            </Menu>
          </Box>
        </Container>
      </>
    );
  };
  
  export default Cash;